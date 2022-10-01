const {runTurn} = require("../src/javascriptRunner");

test("givenDemoBotAndSimpleGame_whenRunningSimpleTurn_shouldChangeCastleAction", () => {
    const bot = require('../../../../../test/resources/botRunner/demoJsCode');
    const owner = 0;
    const simpleGameState = JSON.stringify(require('../../../../../test/resources/botRunner/simpleGameState.json'));
    const expectedGameState = JSON.stringify(require('../../../../../test/resources/botRunner/simpleGameAfterTurnState.json'));

    const actualNewGameState = runTurn(simpleGameState, bot, owner);

    expect(actualNewGameState).toEqual(JSON.parse(expectedGameState))
});

// test("givenANoneValidAction_should", () => {
//     const bot = require('../../../../testResources/botRunner/demoJsCode');
//     const player = 0;
//     const simpleGameState = JSON.stringify(require('../../../../testResources/botRunner/simpleGameState.json'));
//     const expectedGameState = JSON.stringify(require('../../../../testResources/botRunner/simpleGameAfterTurnState.json'));
//
//     const actualNewGameState = runTurn(simpleGameState, bot, player);
//
//
//     expect(actualNewGameState).toEqual(JSON.parse(expectedGameState))
// });