import MoveAction from "../src/classes/actions/MoveAction";
import Loc from "../src/classes/Loc";
import Game from "../src/Game";

const {runTurn} = require("../src/typescriptRunner");
// TODO this ts file can be better
test("GIVEN demo bot and simple game WHEN running simple turn SHOULD change castle action", () => {
    const bot = require('../../../../../test/resources/apis/ts/demoTsCode');
    const owner = 5;
    const simpleGameState = JSON.stringify(require('../../../../../test/resources/simpleGameState.json'));

    const expectedActions = [new MoveAction(1, new Loc(1000, 1000), owner)]

    const actualActions = runTurn(simpleGameState, owner, bot) as Game

    expect(actualActions.actions).toEqual(expectedActions)
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