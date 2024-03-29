const scalaVersion = require('./scala-version')

module.exports = (api) => {
  const scalajsMode = api.mode === 'production' ? 'opt' : 'fastopt'
  return {
    purge: [
      `./modules/frontend/.js/target/scala-${scalaVersion}/frontend-${scalajsMode}/*.js`,
      './modules/frontend/src/main/static/**/*.html',
    ],
    plugins: [
      require('@tailwindcss/forms'),
      require('@tailwindcss/typography'),
      require('@tailwindcss/aspect-ratio')
    ],
  }
}
