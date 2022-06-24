const {Game} = require("../gameAPIs/game")

const botPath = process.argv[2];
const side = process.argv[3] || -1;

const bot = require(botPath);

process.stdin.on('readable', () => {
    let gameStateString;
    while ((gameStateString = process.stdin.read()) !== null) {
        const gameState = new Game(gameStateString, side);
        const updatedGameState = bot.doTurn(gameState)
        process.stdout.write(`${JSON.stringify(updatedGameState)}\n`);

    }
})
// console.log(`[{"actionId": "q","side": 1,"affectedId": "1","soldierType": 1}]`);

// node JavascriptCaller.js E:\ComputerScience\Projects\war-scripts-lib\testResources\demoJsCode.js {"mapData":{"rows":20,"cols":20,"players":[0,1]},"gameObjects":[{"objectType":"CASTLE","side":0,"id":"0","soldierType":"MELEE","loc":{"row":1,"col":1}},{"objectType":"CASTLE","side":1,"id":"1","soldierType":"MELEE","loc":{"row":19,"col":19}},{"objectType":"CASTLE","side":-1,"id":"2","soldierType":"MELEE","loc":{"row":1,"col":19}},{"objectType":"CASTLE","side":-1,"id":"3","soldierType":"MELEE","loc":{"row":19,"col":1}}]} 1


