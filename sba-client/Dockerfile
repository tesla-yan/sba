FROM node:10
WORKDIR /usr/src/app
COPY server/package*.json ./
RUN npm install
COPY server .
CMD [ "node", "server.js" ]
