import Game from "./Game"


// TODO: This is not really tested well
process.stdin.on('readable', () => {
        const owner = parseInt(process.argv[3]) || -1;
        const botModule = require(process.argv[2]);
        let gameStateString;
        while ((gameStateString = process.stdin.read()) !== null) {
            try {
                const newGameState = runTurn(gameStateString, owner, botModule);

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
function runTurn(gameStateString: string, owner: number, bot: { doTurn: Function }) {
    const gameState = new Game(gameStateString, owner);

    return bot.doTurn(gameState)
}

module.exports = {
    runTurn
}
