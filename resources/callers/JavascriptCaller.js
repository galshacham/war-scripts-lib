const { Game } = require("../gameAPIs/game")

const toStream = process.argv[2];
const jsonGameState = '"' + process.argv[3] + '"';
const side = process.argv[4] || -1;

const bot = require(toStream);

const game = new Game(jsonGameState, side);


bot.doTurn(game)
console.log("___start___")
console.log({actions: game.actions})

