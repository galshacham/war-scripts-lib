const {Game} = require("../src/game");
const {runTurn} = require("../src/javascriptRunner");

test("givenDemoBotAndSimpleGame_whenRunningSimpleTurn_shouldChangeCastleAction", () => {
    const bot = require('../../../../testResources/botRunner/demoJsCode');
    const player = 0;
    const simpleGameState = JSON.stringify(require('../../../../testResources/botRunner/simpleGameState.json'));
    const expectedGameState = JSON.stringify(require('../../../../testResources/botRunner/simpleGameAfterTurnState.json'));

    const actualNewGameState = runTurn(simpleGameState, bot, player);


    expect(actualNewGameState).toEqual(JSON.parse(expectedGameState))
});