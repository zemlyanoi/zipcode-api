FROM node:8.10

# The base node image sets a very verbose log level.
ENV NPM_CONFIG_LOGLEVEL warn

# Copy all local files into the image.
COPY . .

# Install `serve` to run the application.
RUN yarn install

# command to start the node server.
CMD yarn build

CMD yarn start

EXPOSE 3000