// const {runTurn} = require("../src/javascriptRunner");

test("GIVEN demo bot and simple game WHEN running simple turn SHOULD change castle action", () => {
    const bot = require('../../../../../test/resources/apis/js/demoJsCode');
    // const owner = 0;
    const simpleGameState = JSON.stringify(require('../../../../../test/resources/simpleGameState.json'));
    const expectedGameState = JSON.stringify(require('../../../../../test/resources/simpleExpectedActions.json'));

    // const actualNewGameState = runTurn(simpleGameState, bot);

    // expect(actualNewGameState).toEqual(JSON.parse(expectedGameState))
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