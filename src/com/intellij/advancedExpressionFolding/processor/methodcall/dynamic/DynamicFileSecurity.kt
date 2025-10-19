package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.openapi.diagnostic.Logger
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.AclEntry
import java.nio.file.attribute.AclEntryFlag
import java.nio.file.attribute.AclEntryPermission
import java.nio.file.attribute.AclEntryType
import java.nio.file.attribute.PosixFilePermissions
import java.nio.file.attribute.PosixFilePermission
import java.util.EnumSet
import kotlin.io.path.exists

internal object DynamicFileSecurity {
    private val logger: Logger = Logger.getInstance(DynamicFileSecurity::class.java)

    private val insecurePosixPermissions = setOf(
        PosixFilePermission.GROUP_READ,
        PosixFilePermission.GROUP_WRITE,
        PosixFilePermission.GROUP_EXECUTE,
        PosixFilePermission.OTHERS_READ,
        PosixFilePermission.OTHERS_WRITE,
        PosixFilePermission.OTHERS_EXECUTE
    )

    private val secureDirectoryPermissions = PosixFilePermissions.fromString("rwx------")
    private val secureFilePermissions = PosixFilePermissions.fromString("rw-------")

    fun ensureDirectorySecure(directory: Path) {
        runCatching { Files.createDirectories(directory) }
            .onFailure { logger.warn("Failed to create configuration directory $directory", it) }

        if (!directory.exists()) {
            return
        }

        setPosixPermissions(directory, isDirectory = true)
        setWindowsPermissions(directory, isDirectory = true)
    }

    fun ensureFileSecure(file: Path) {
        if (!Files.exists(file)) {
            return
        }

        setPosixPermissions(file, isDirectory = false)
        setWindowsPermissions(file, isDirectory = false)
    }

    fun isOwnedByCurrentUser(path: Path): Boolean {
        val owner = runCatching { Files.getOwner(path) }
            .getOrElse {
                logger.warn("Failed to resolve file owner for $path", it)
                return false
            }

        val currentUser = System.getProperty("user.name") ?: return false

        if (owner.name == currentUser || owner.name.endsWith("\\$currentUser", ignoreCase = true)) {
            return true
        }

        val lookupService = runCatching { path.fileSystem.userPrincipalLookupService }
            .getOrElse {
                logger.warn("Failed to obtain user principal lookup service for $path", it)
                return false
            }

        val currentPrincipal = runCatching { lookupService.lookupPrincipalByName(currentUser) }.getOrNull()
        return currentPrincipal != null && (owner == currentPrincipal || owner.name == currentPrincipal.name)
    }

    fun hasWorldAccessiblePermissions(path: Path): Boolean {
        if (!supportsPosix()) {
            return false
        }

        return runCatching { Files.getPosixFilePermissions(path) }
            .onFailure { logger.warn("Failed to read POSIX permissions for $path", it) }
            .getOrNull()
            ?.any { it in insecurePosixPermissions }
            ?: false
    }

    private fun setPosixPermissions(path: Path, isDirectory: Boolean) {
        if (!supportsPosix()) {
            return
        }

        val permissions = if (isDirectory) secureDirectoryPermissions else secureFilePermissions
        runCatching { Files.setPosixFilePermissions(path, permissions) }
            .onFailure { logger.warn("Failed to set POSIX permissions for $path", it) }
    }

    private fun setWindowsPermissions(path: Path, isDirectory: Boolean) {
        if (supportsPosix() || !supportsAcl()) {
            return
        }

        val owner = runCatching { Files.getOwner(path) }.getOrElse {
            logger.warn("Failed to resolve owner while updating ACL for $path", it)
            return
        }

        val entryBuilder = AclEntry.newBuilder()
            .setType(AclEntryType.ALLOW)
            .setPrincipal(owner)
            .setPermissions(EnumSet.allOf(AclEntryPermission::class.java))

        if (isDirectory) {
            entryBuilder.setFlags(AclEntryFlag.FILE_INHERIT, AclEntryFlag.DIRECTORY_INHERIT)
        }

        runCatching { Files.setAttribute(path, "acl:acl", listOf(entryBuilder.build())) }
            .onFailure { logger.warn("Failed to update ACL permissions for $path", it) }
    }

    private fun supportsPosix(): Boolean =
        FileSystems.getDefault().supportedFileAttributeViews().contains("posix")

    private fun supportsAcl(): Boolean =
        FileSystems.getDefault().supportedFileAttributeViews().contains("acl")
}
