/*global require, window*/
require.config({
    baseUrl: '../src',
    paths: {
        'jquery': ['../test/js/libs/jquery.min'],
        'jasmine': ['../test/js/libs/jasmine'],
        'jasmine-html': ['../test/js/libs/jasmine-html'],
        'jasmine-boot': ['../test/js/libs/boot'],
        'toy-robot-simulator': ['../src/js/simulator'],
        'simulator-spec': ['../test/js/spec/SimulatorISpec']
    },
    shim: {
        'jasmine-html': {
            deps: ['jasmine']
        },
        'jasmine-boot': {
            deps: ['jasmine', 'jasmine-html']
        }
    }
});

(function () {

    'use strict';

    require(['jasmine-boot'], function () {
        require(['hello-world-spec'], function () {
            window.onload();
        });
    });

}());