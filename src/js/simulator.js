/*global $*/
var SIMULATOR = (function () {

    'use strict';

    function SIM() {

        this.CONFIG = {
            version: 0.1,
            table: {
                length: 5,
                height: 5
            },
            commands: ['PLACE', 'MOVE', 'LEFT', 'RIGHT', 'REPORT'],
            position: {
                x: 0,
                y: 0,
                facing: null
            },
            command: null
        };

    }

    SIM.prototype.init = function (config) {

        /* Extend default configuration. */
        this.CONFIG = $.extend(true, {}, this.CONFIG, config);

    };

    SIM.prototype.execute = function (command) {
        if (command === undefined || command === null) {
            throw new Error('Please provide a valid command.');
        }
        command = command.toUpperCase();
        if (this.CONFIG.command === null && command !== 'PLACE') {
            throw new Error('The first valid command to the robot must be the PLACE command.');
        }
    };

    SIM.prototype.place = function (x, y, facing) {

    };

    return SIM;

}());