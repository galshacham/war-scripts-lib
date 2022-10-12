const {Loc, Castle, Game, GameData} = require("../../../../../main/resources/apis/js/src/game");
const {MoveAction} = require("../src/game");

const basicGameState = {
    "gameData": {
        "maxTurns": 1000,
        "currentTurn": 0
    },
    "objects": {
        "0": {
            "type": "CASTLE",
            "id": 0,
            "soldierType": "MELEE",
            "loc": {
                "row": 1,
                "col": 1
            }
        },
        "1": {
            "type": "CASTLE",
            "id": 1,
            "soldierType": "MELEE",
            "loc": {
                "row": 19,
                "col": 19
            }
        },
        "2": {
            "type": "CASTLE",
            "id": 2,
            "soldierType": "MELEE",
            "loc": {
                "row": 1,
                "col": 19
            }
        },
        "3": {
            "type": "CASTLE",
            "id": 3,
            "soldierType": "MELEE",
            "loc": {
                "row": 19,
                "col": 1
            }
        }
    }
}

const gameStateWithSoldiers = {
    "gameData": {
        "maxTurns": 1000,
        "currentTurn": 0
    },
    "objects": {
        "0": {
            "type": "CASTLE",
            "id": 0,
            "soldierType": "MELEE",
            "loc": {
                "row": 1,
                "col": 1
            }
        },
        "1": {
            "type": "CASTLE",
            "id": 1,
            "soldierType": "MELEE",
            "loc": {
                "row": 19,
                "col": 19
            }
        },
        "2": {
            "type": "CASTLE",
            "id": 2,
            "soldierType": "MELEE",
            "loc": {
                "row": 1,
                "col": 19
            }
        },
        "3": {
            "type": "CASTLE",
            "id": 3,
            "soldierType": "MELEE",
            "loc": {
                "row": 19,
                "col": 1
            }
        },
        "4": {
            "type": "SOLDIER",
            "id": 4,
            "speed": 4,
            "loc": {
                "row": 0,
                "col": 0
            }
        }, "5": {
            "type": "SOLDIER",
            "id": 5,
            "speed": 3,
            "loc": {
                "row": 5,
                "col": 5
            }
        }
    }
}

const basicGameString = JSON.stringify(basicGameState);
const gameStateWithSoldiersString = JSON.stringify(gameStateWithSoldiers);
describe("Game Tests", () => {
    describe("Game Initialization", () => {
        const expectedGameObject = {
            // owner: 1,
            // logs: [],
            actions: [],
            gameData: new GameData(1000, 0),
            objects: {
                "0": new Castle(0, new Loc(1, 1), "MELEE"),
                "1": new Castle(1, new Loc(19, 19), "MELEE"),
                "2": new Castle(2, new Loc(19, 1), "MELEE"),
                "3": new Castle(3, new Loc(1, 19), "MELEE"),
            }
        };
        expectedGameObject.__proto__ = Game.prototype;


        test("WHEN initializing game from json state SHOULD construct new game", () => {
            const game = new Game(basicGameString/*, 1*/);

            expect(game).toStrictEqual(expectedGameObject);
        });
    })

    describe("Api tests", () => {
        test("GIVEN basicGame WHEN moving a soldier SHOULD add action to list", () => {
            const game = new Game(gameStateWithSoldiersString);
            const expectedGame = new Game(gameStateWithSoldiersString);

            const soldierId = 4
            const loc = new Loc(5, 5)
            expectedGame.actions = [new MoveAction(soldierId, loc)]

            game.moveSoldier(soldierId, loc)

            expect(game).toStrictEqual(expectedGame);
        })
        describe("In Range Tests", () => {
            const loc1 = new Loc(0, 0)
            const loc2 = new Loc(0, 5)

            test("WHEN loc in range of another loc SHOULD return 1", () => {
                expect(1).toEqual(loc1.inRange(loc2, 10))
            })
            test("WHEN loc in exactly range of another loc SHOULD return 0", () => {
                expect(0).toEqual(loc1.inRange(loc2, 5))
            })
            test("WHEN loc not in range of another loc SHOULD return -1", () => {
                expect(-1).toEqual(loc1.inRange(loc2, 3))
            })
        })

        describe("Get Directions Tests", () => {
            test("GIVEN basicGame WHEN getting directions right only SHOULD return list of possible right location only", () => {
                const game = new Game(gameStateWithSoldiersString);

                const soldierId = 4
                const desiredLocation = new Loc(5, 0)

                const expectedDirections = [
                    new Loc(4, 0)
                ]

                const actualDirections = game.getDirections(soldierId, desiredLocation)

                expect(actualDirections).toStrictEqual(expectedDirections);
            })

            test("GIVEN basicGame WHEN getting directions up only SHOULD return list of possible up only", () => {
                const game = new Game(gameStateWithSoldiersString);

                const soldierId = 4
                const desiredLocation = new Loc(0, 5)

                const expectedDirections = [
                    new Loc(0, 4),
                ]

                const actualDirections = game.getDirections(soldierId, desiredLocation)

                expect(actualDirections).toStrictEqual(expectedDirections);
            })

            test("GIVEN basicGame WHEN getting directions up and right SHOULD return list of possible locations", () => {
                const game = new Game(gameStateWithSoldiersString);

                const soldierId = 4
                const desiredLocation = new Loc(5, 5)

                const expectedDirections = [
                    new Loc(4, 0),
                    new Loc(3, 1),
                    new Loc(2, 2),
                    new Loc(1, 3),
                    new Loc(0, 4),
                ]

                const actualDirections = game.getDirections(soldierId, desiredLocation)

                expect(actualDirections).toStrictEqual(expectedDirections);
            })

            test("GIVEN basicGame WHEN getting directions down and left SHOULD return list of possible locations", () => {
                const game = new Game(gameStateWithSoldiersString);

                const soldierId = 5
                const desiredLocation = new Loc(0, 0)

                const expectedDirections = [
                    new Loc(2, 5),
                    new Loc(3, 4),
                    new Loc(4, 3),
                    new Loc(5, 2),
                ]

                const actualDirections = game.getDirections(soldierId, desiredLocation)

                expect(actualDirections).toStrictEqual(expectedDirections);
            })
        })
    })
})


// test("GIVEN valid game WHEN getting allMyCastles SHOULD return only my castles", () => {
//     const game = new Game(basicGameString, -1);
//     const expectedCastles = [
//         new Castle(2, -1, new Loc(1, 19), "MELEE"),
//         new Castle(3, -1, new Loc(19, 1), "MELEE"),
//     ];
//
//     expect(game.getAllMyCastles()).toStrictEqual(expectedCastles);
// });

// test("GIVEN game when changing soldierType SHOULD add correct action to castle", () => {
//     const game = new Game(basicGameString)
//
//     const newSoldierType = "RANGED";
//     const castleId = 0;
//
//     game.changeSoldierType(castleId, newSoldierType);
//
//     expect(game.actions).toStrictEqual([new ChangeSoldierTypeAction(castleId, newSoldierType)]);
// });

// test("GIVEN game WHEN adding debug game logs SHOULD add push logs", () => {
//     const game = new Game(basicGameString, 1)
//     const expectedGame = new Game(basicGameString, 1)
//     const logMessage1 = "hello"
//     const logMessage2 = "world"
//     expectedGame.logs = [logMessage1, logMessage2]
//     game.log(logMessage1)
//     game.log(logMessage2)
//     expect(game).toStrictEqual(expectedGame);
// });