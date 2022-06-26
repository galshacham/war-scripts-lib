const {Game} = require("./game")

const botPath = process.argv[2];
const player = process.argv[3] || -1;

const bot = require(botPath);

process.stdin.on('readable', () => {
    let gameStateString;
    while ((gameStateString = process.stdin.read()) !== null) {
        const gameState = new Game(gameStateString, player);
        const updatedGameState = bot.doTurn(gameState)
        process.stdout.write(`${JSON.stringify(updatedGameState)}\n`);
    }
})

