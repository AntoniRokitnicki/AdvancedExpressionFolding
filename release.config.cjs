module.exports = {
  branches: ['main'],
  tagFormat: 'v${version}',
  plugins: [
    [
      '@semantic-release/commit-analyzer',
      {
        preset: 'conventionalcommits',
        releaseRules: [
          { type: 'release', scope: 'major', release: 'major' },
          { type: 'release', scope: 'minor', release: 'minor' },
          { type: 'release', scope: 'release', release: 'patch' },
          { breaking: true, release: false },
          { type: '*', release: false },
        ],
      },
    ],
    '@semantic-release/release-notes-generator',
    '@semantic-release/github',
  ],
};
