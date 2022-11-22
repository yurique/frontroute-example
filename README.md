# frontroute example

[frontroute](https://github.com/tulz-app/frontroute) + [Laminar](https://github.com/raquo/Laminar) example project.

## Running

Install npm dependencies:

```
yarn 
```

Build the front-end:

```
sbt frontendJS/fastLinkJS
```

Start the webpack dev server:

```
yarn dev
```

## Open in a browser 

Open http://localhost:30290/ in the browser.

## Developing

To make sbt re-compile the front-end on code changes:

```
sbt ~frontendJS/fastLinkJS
```

## Production build

Build an optimized JS:

```
sbt frontendJS/fullLinkJS
```

Run webpack:

```
yarn run build
```

The front-end assets will be generated into the `dist` folder.
