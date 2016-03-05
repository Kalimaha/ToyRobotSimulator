/*global describe, SIMULATOR, beforeEach, it, expect*/
describe('Toy Robot Simulator', function () {

    'use strict';

    var s;

    beforeEach(function () {
        s = new SIMULATOR();
    });

    describe('Configurability', function () {

        it('The simulator should have a version number', function () {
            expect(s.CONFIG.version).not.toBeUndefined();
        });

        it('with current version equal to 0.1', function () {
            expect(s.CONFIG.version).toEqual(0.1);
        });

        it('that can be overridden to 0.2', function () {
            s.init({
                version: 0.2
            });
            expect(s.CONFIG.version).toEqual(0.2);
        });

        it('The simulator accepts 5 different commands', function () {
            expect(s.CONFIG.commands.length).toEqual(5);
        });

        it('The simulator has a table that is 5 units wide', function () {
            expect(s.CONFIG.table.length).toEqual(5);
        });

        it('and 5 units long', function () {
            expect(s.CONFIG.table.height).toEqual(5);
        });

    });

    describe('Initialization: Single Value', function () {

        it('The simulation starts with the PLACE command. NULL throws an error.', function () {
            expect(function () {
                s.execute(null);
            }).toThrowError('Please provide a valid command.');
        });

        it('The simulation starts with the PLACE command. UNDEFINED throws an error.', function () {
            expect(function () {
                s.execute();
            }).toThrowError('Please provide a valid command.');
        });

        it('The simulation starts with the PLACE command. MOVE throws an error.', function () {
            expect(function () {
                s.execute('MOVE');
            }).toThrowError('The first valid command to the robot must be the PLACE command.');
        });

        it('The simulation starts with the PLACE command. LEFT throws an error.', function () {
            expect(function () {
                s.execute('LEFT');
            }).toThrowError('The first valid command to the robot must be the PLACE command.');
        });

        it('The simulation starts with the PLACE command. RIGHT throws an error.', function () {
            expect(function () {
                s.execute('RIGHT');
            }).toThrowError('The first valid command to the robot must be the PLACE command.');
        });

        it('The simulation starts with the PLACE command. REPORT throws an error.', function () {
            expect(function () {
                s.execute('REPORT');
            }).toThrowError('The first valid command to the robot must be the PLACE command.');
        });

    });

    describe('Initialization: Array', function () {

        it('Any other commands prior to PLACE will be discarded.', function () {
            s.execute(['move', 'report', 'place 0,0,north']);
            expect(s.CONFIG.start_idx).toEqual(2);
        });

        it('The robot must be placed ON the table: x cannot exceeds 4.', function () {
            expect(function () {
                s.place(5, 0, 'NORTH');
            }).toThrowError('Please place the robot ON the table.');
        });

        it('The robot must be placed ON the table: x cannot be smaller than 0.', function () {
            expect(function () {
                s.place(-1, 0, 'NORTH');
            }).toThrowError('Please place the robot ON the table.');
        });

        it('The robot must be placed ON the table: y cannot exceeds 4.', function () {
            expect(function () {
                s.place(0, 5, 'NORTH');
            }).toThrowError('Please place the robot ON the table.');
        });

        it('The robot must be placed ON the table: y cannot be smaller than 0.', function () {
            expect(function () {
                s.place(0, -1, 'NORTH');
            }).toThrowError('Please place the robot ON the table.');
        });

    });

    describe('Commands: PLACE', function () {

        it('The PLACE command alters the current X location of the robot.', function () {
            s.place(3, 2, 'north');
            expect(s.CONFIG.position.x).toEqual(3);
        });

        it('The PLACE command alters the current Y location of the robot.', function () {
            s.place(3, 2, 'north');
            expect(s.CONFIG.position.y).toEqual(2);
        });

        it('The PLACE command alters the current FACING location of the robot.', function () {
            s.place(3, 2, 'north');
            expect(s.CONFIG.position.facing).toEqual('NORTH');
        });

        it('The user must provide a valid facing direction.', function () {
            expect(function () {
                s.place(0, 0, 'back');
            }).toThrowError('Please provide a valid facing direction: NORTH, SOUTH, EAST or WEST');
        });

    });

    describe('Commands: MOVE', function () {

        it('MOVE will move the toy robot one unit forward in the direction it is currently facing. Example: NORTH', function () {
            s.place(3, 2, 'north');
            s.move();
            expect(s.CONFIG.position.y).toEqual(3);
        });

        it('MOVE will move the toy robot one unit forward in the direction it is currently facing. Example: SOUTH', function () {
            s.place(3, 2, 'south');
            s.move();
            expect(s.CONFIG.position.y).toEqual(1);
        });

        it('MOVE will move the toy robot one unit forward in the direction it is currently facing. Example: EAST', function () {
            s.place(3, 2, 'east');
            s.move();
            expect(s.CONFIG.position.x).toEqual(4);
        });

        it('MOVE will move the toy robot one unit forward in the direction it is currently facing. Example: WEST', function () {
            s.place(3, 2, 'west');
            s.move();
            expect(s.CONFIG.position.x).toEqual(2);
        });

        it('The robot will refuse to move outside the table.', function () {
            s.place(4, 2, 'east');
            s.move();
            expect(s.CONFIG.position.x).toEqual(4);
        });

    });

    describe('Commands: LEFT', function () {

        it('LEFT will rotate the robot 90 degrees in the specified direction: from NORTH to WEST.', function () {
            s.place(3, 2, 'north');
            s.left();
            expect(s.CONFIG.position.facing).toEqual('WEST');
        });

        it('LEFT will rotate the robot 90 degrees in the specified direction: from WEST to SOUTH.', function () {
            s.place(3, 2, 'west');
            s.left();
            expect(s.CONFIG.position.facing).toEqual('SOUTH');
        });

        it('LEFT will rotate the robot 90 degrees in the specified direction: from SOUTH to EAST.', function () {
            s.place(3, 2, 'south');
            s.left();
            expect(s.CONFIG.position.facing).toEqual('EAST');
        });

        it('LEFT will rotate the robot 90 degrees in the specified direction: from EAST to NORTH.', function () {
            s.place(3, 2, 'east');
            s.left();
            expect(s.CONFIG.position.facing).toEqual('NORTH');
        });

    });

    describe('Commands: RIGHT', function () {

        it('RIGHT will rotate the robot 90 degrees in the specified direction: from NORTH to EAST.', function () {
            s.place(3, 2, 'north');
            s.right();
            expect(s.CONFIG.position.facing).toEqual('EAST');
        });

        it('RIGHT will rotate the robot 90 degrees in the specified direction: from EAST to SOUTH.', function () {
            s.place(3, 2, 'east');
            s.right();
            expect(s.CONFIG.position.facing).toEqual('SOUTH');
        });

        it('RIGHT will rotate the robot 90 degrees in the specified direction: from SOUTH to WEST.', function () {
            s.place(3, 2, 'south');
            s.right();
            expect(s.CONFIG.position.facing).toEqual('WEST');
        });

        it('RIGHT will rotate the robot 90 degrees in the specified direction: from WEST to NORTH.', function () {
            s.place(3, 2, 'west');
            s.right();
            expect(s.CONFIG.position.facing).toEqual('NORTH');
        });

    });

    describe('Commands: REPORT', function () {

        it('REPORT will announce the X, Y and F of the robot.', function () {
            s.place(3, 2, 'north');
            expect(s.report()).toEqual('X: 3, Y: 2, FACING: NORTH');
        });

    });

    describe('Execution', function () {

        it('Execution plan: PLACE 0,0,NORTH then MOVE then REPORT', function () {
            s.execute(['PLACE 0,0,NORTH', 'MOVE', 'REPORT']);
            expect(s.report()).toEqual('X: 0, Y: 1, FACING: NORTH');
        });

        it('Execution plan: PLACE 0,0,NORTH then MOVE then REPORT', function () {
            s.execute(['PLACE 0,0,NORTH', 'LEFT', 'REPORT']);
            expect(s.report()).toEqual('X: 0, Y: 0, FACING: WEST');
        });

        it('Execution plan: PLACE 1,2,EAST then MOVE, then MOVE, then LEFT then MOVE then REPORT', function () {
            s.execute(['PLACE 1,2,EAST', 'MOVE', 'MOVE', 'LEFT', 'MOVE', 'REPORT']);
            expect(s.report()).toEqual('X: 3, Y: 3, FACING: NORTH');
        });

    });

});