# frontroute example

An example project for the `frontroute`: https://github.com/tulz-app/frontroute

## Running

Install npm dependencies:

```
yarn install
```

Build the front-end:

```
sbt frontendJS/fastLinkJS
```

Start the webpack dev server:

```
yarn start
```

## Open in a browser 

Open http://localhost:30290/ in the browser.

## Developing

To make sbt re-compile the front-end on code changes:

```
sbt ~frontendJS/fastLinkJS
```

## Production build

Build an optimized js for the front-end:

```
sbt frontendJS/fullLinkJS
```

Run the webpack:

```
yarn run build:prod
```

The front-end assets will be generated into the `dist` folder.
