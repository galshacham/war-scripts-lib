const {Game} = require("./game")

const botPath = process.argv[2];
// const owner = parseInt(process.argv[3]) || -1;


process.stdin.on('readable', () => {
        const botModule = require(botPath);
        let gameStateString;
        while ((gameStateString = process.stdin.read()) !== null) {
            try {
                const newGameState = runTurn(gameStateString, botModule)//, owner);

                process.stdout.write(`${JSON.stringify(newGameState.actions)}\n`);
            } catch (e) {
                // TODO: change this
                process.stdout.write(`ERROR---${e}\n`);
                // process.stdout.write(`${gameStateString}, ${owner} ${botPath} \n`);
            }
        }
    }
)

// This looks funny, but this is here so bots functionality can be tested
function runTurn(gameStateString, bot) {
    const gameState = new Game(gameStateString);
    return bot.doTurn(gameState)
}

module.exports = {
    runTurn
}
