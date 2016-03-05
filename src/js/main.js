/*global require,document*/
require.config({

    baseUrl: 'src/js/libs',

    paths: {

        toy_robot_simulator: '../simulator'

    },

    shim: {
        bootstrap: ['jquery'],
        toy_robot_simulator: ['jquery']
    }

});

require(['jquery', 'toy_robot_simulator', 'bootstrap'], function ($, SIMULATOR) {

    'use strict';

    $('#execute').click(function () {
        var s = new SIMULATOR(),
            placeholder = $('#report');
        placeholder.empty();
        var usr = $('#commands').val().split(';'),
            commands = [],
            i;
        for (i = 0; i < usr.length; i += 1) {
            if (usr[i].length > 0) {
                commands.push(usr[i].trim());
            }
        }
        s.execute(commands);
        for (i = 0; i < s.CONFIG.reports.length; i += 1) {
            placeholder.append('<div>' + s.CONFIG.reports[i] + '</div><br>');
        }
    });

});