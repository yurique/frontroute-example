const colors = require('tailwindcss/colors')
const scalaVersion = require('./scala-version')

module.exports = (api) => {
  const scalajsMode = api.mode === 'production' ? 'opt' : 'fastopt'
  return {
    mode: 'jit',
    purge: [
      `./modules/frontend/.js/target/scala-${scalaVersion}/frontend-${scalajsMode}/*.js`,
      './modules/frontend/src/static/**/*.html',
    ],
    corePlugins: {},
    plugins: [
      require('@tailwindcss/forms'),
      require('@tailwindcss/typography'),
      require('@tailwindcss/aspect-ratio')
    ],
  }
}