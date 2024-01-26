package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import org.jetbrains.annotations.NonNls

class MapStructExpression(
    val m: PsiMethod
) : Expression(m, m.textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {

        //public void setData(MapstructTestData data) {
        //        this.data = data;
        //    }
        // =>
        //    @Mapping(target = "data", source = "data")
        //    void setData(MapstructTestData data);

        val c2 = m.children
        //0 = {PsiModifierListImpl@15918} public [PsiKeyword:public]
        //1 = {PsiTypeParameterListImpl@15919}  []
        //2 = {PsiWhiteSpaceImpl@15920}   []
        //3 = {PsiTypeElementImpl@15921} void [PsiKeyword:void]
        //4 = {PsiWhiteSpaceImpl@15922}   []
        //5 = {PsiIdentifierImpl@15923} setData []
        //6 = {PsiParameterListImpl@15924} (MapstructTestData data) [PsiJavaToken:LPARENTH,PsiParameter:data,PsiJavaToken:RPARENTH]
        //7 = {PsiReferenceListImpl@15925}  []
        //8 = {PsiWhiteSpaceImpl@15926}   []
        //9 = {PsiCodeBlockImpl@15927} {\n        this.data = data;\n    } [PsiJavaToken:LBRACE,PsiWhiteSpace,PsiExpressionStatement,PsiWhiteSpace,PsiJavaToken:RBRACE]
        val body = c2[9]
        val c = body.children
        //m.children[9].children = {PsiElement[5]@16052}
        // 0 = {PsiJavaTokenImpl@15979} { []
        // 1 = {PsiWhiteSpaceImpl@15980} \n         []
        // 2 = {PsiExpressionStatementImpl@15981} this.data = data; [PsiAssignmentExpression:this.data = data,PsiJavaToken:SEMICOLON]
        // 3 = {PsiWhiteSpaceImpl@15982} \n     []
        // 4 = {PsiJavaTokenImpl@15983} } []
        var a = mutableListOf<FoldingDescriptor>()


        val t1 = "@Mapping(\n\n\n = \"data\", source = \""
               // "data\")"

        var s = m.s()
        var e = m.s() + "public void setData(MapstructTestData2 data) {".length - 7  // can't do -3
        a += fold(m, s, e, t1, "a")

        s = e +4
        e = s +1
        a += fold(m, s, e, "\")", "a")

        s = e +1
        e = s +4 + 6
        a += fold(m, s, e, "", "a")

        val f = c[2]
        s = f.s()
        e = f.e()
        a += fold(body, s, e, "", "a2")
        val ss = c[4]
        s = ss.s()
        e = ss.e()
        a += fold(ss, s, e, "void setData(MapstructTestData data)", "a3")
        //a += fold(m, s, e, "")



        return a.toTypedArray()
    }


    fun PsiElement.s(): Int = textRange.startOffset
    fun PsiElement.e(): Int = textRange.endOffset

    private fun fold(
        element: PsiElement,
        startOffset: Int,
        endOffset: Int,
        placeholderText: String,
        group: @NonNls String
    ): FoldingDescriptor {
        return FoldingDescriptor(element.node, TextRange.create(startOffset, endOffset), FoldingGroup.newGroup(CustomClassAnnotationExpression::class.java.name + group), placeholderText)
    }
}
