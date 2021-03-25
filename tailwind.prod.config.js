const _ = require('lodash');
const base = require('./tailwind.base.config');

const config = _.mergeWith({}, base, {
  purge: {
    content: [
      './modules/frontend/.js/target/scala-2.13/frontend-opt/*.js',
      './modules/frontend/src/static/**/*.html',
    ]
  },
});

module.exports = config;
