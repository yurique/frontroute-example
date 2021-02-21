module.exports = {
  theme: {
    extend: {
      fontFamily: {
        condensed: [
          'Roboto Condensed'
        ]
      }
    }
  },
  plugins: [
    require('@tailwindcss/typography'),
    require('@tailwindcss/forms'),
  ]
}
