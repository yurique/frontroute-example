const path = require('path');
const _ = require('lodash');

const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');
const TerserPlugin = require('terser-webpack-plugin')
const CompressionPlugin = require('compression-webpack-plugin');

const scalaOutputPath = path.resolve(__dirname, './modules/frontend/.js/target/scala-2.13');

const devServerHost = '127.0.0.1';
const devServerPort = 30290;

const devServer = {
  host: devServerHost,
  port: devServerPort,
  historyApiFallback: {
    index: ''
  },
  static: {
    directory: path.resolve(__dirname, 'modules/frontend/dev-config'),
    staticOptions: {},
    publicPath: ['/config/'],
    serveIndex: false,
    watch: true,
  },
};

function common(mode) {
  return {
    mode: mode,
    entry: [
      path.resolve(__dirname, './modules/frontend/src/main/static/stylesheets/main.css'),
    ],
    output: {
      publicPath: '/',
      path: path.resolve(__dirname, 'dist'),
      filename: '[name].bundle.[contenthash].js'
    },
    module: {
      rules: [
        {
          test: /\.css$/i,
          use: [MiniCssExtractPlugin.loader, 'css-loader', 'postcss-loader'],
        },
        {
          test: /\.(png|jpg|woff(2)?|ttf|eot|svg)$/,
          type: 'asset/resource'
        },
      ]
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: './modules/frontend/src/main/static/html/index.html.ejs',
        minify: false,
        inject: 'head',
        mode: mode,
        scriptLoading: 'defer'
      }),
      new CopyWebpackPlugin({
        patterns: [
          {
            from: './modules/frontend/src/main/public',
            to: ''
          }
        ]
      })
    ]
  }
}

const prod = {
  entry: [
    path.resolve(__dirname, `${scalaOutputPath}/frontend-opt/main.js`),
  ],
  optimization: {
    minimize: true,
    minimizer: [new TerserPlugin()],
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: '[name].[contenthash].css',
    }),
    new CleanWebpackPlugin(),
    new CompressionPlugin({
      test: /\.(js|css|html|svg|json|woff|woff2)$/,
      deleteOriginalAssets: false,
    }),
    new CompressionPlugin({
      test: /\.(js|css|html|svg|woff|woff2)$/,
      filename: '[path][base].br',
      algorithm: 'brotliCompress',
      compressionOptions: {
        // zlib’s `level` option matches Brotli’s `BROTLI_PARAM_QUALITY` option.
        level: 11,
      },
      minRatio: 0.8,
      deleteOriginalAssets: false,
    })
  ]
}

const dev = {
  devtool: 'cheap-module-source-map',
  entry: [
    path.resolve(__dirname, `${scalaOutputPath}/frontend-fastopt/main.js`),
  ],
  plugins: [
    new MiniCssExtractPlugin(),
  ]
}

const customizer = (objValue, srcValue) => {
  if (_.isArray(objValue)) {
    return objValue.concat(srcValue);
  }
}


function getConfig() {
  switch (process.env.npm_lifecycle_event) {
    case 'build':
      return _.mergeWith(common('production'), prod, customizer);

    case 'build:dev':
      return _.mergeWith(common('development'), dev, customizer);

    case 'start:prod':
      return _.mergeWith(common('production'), prod, {devServer}, customizer);

    case 'dev':
      return _.mergeWith(common('development'), dev, {devServer}, customizer);

    default:
      console.error(`Unknown: ${process.env.npm_lifecycle_event}`)
  }
}

const config = getConfig()
module.exports = config
