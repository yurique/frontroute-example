module.exports = (api) => {
  return {
    plugins: [
      require('postcss-import')({}),
      // require('@tailwindcss/jit')({}),
      api.mode === 'production' ?
        require('@tailwindcss/jit')('./tailwind.prod.config.js') :
        require('@tailwindcss/jit')('./tailwind.dev.config.js'),
      // require('autoprefixer'),
      // require('postcss-preset-env')({
      //   /* use stage 3 features + css nesting rules */
      //   stage: 3,
      //   features: {
      //     'nesting-rules': true
      //   }
      // }),
      api.mode === 'production' ?
        require('cssnano')() :
        false
    ]
  };
}
