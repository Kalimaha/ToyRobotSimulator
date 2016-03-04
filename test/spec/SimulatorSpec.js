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

    describe('Usage', function () {

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

});