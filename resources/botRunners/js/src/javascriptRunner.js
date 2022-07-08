const {Game} = require("./game")

const botPath = process.argv[2];
const player = process.argv[3] || -1;


process.stdin.on('readable', () => {
    const bot = require(botPath);
    let gameStateString;
    while ((gameStateString = process.stdin.read()) !== null) {
        const newGameState = runTurn(gameStateString, bot, player);

        process.stdout.write(`${newGameState}\n`);
    }
})

// This looks funny, but this is here so bots functionality can be tested
function runTurn(gameStateString, bot, player) {
    const gameState = new Game(gameStateString, player);
    return bot.doTurn(gameState)
}

module.exports = {
    runTurn
}
