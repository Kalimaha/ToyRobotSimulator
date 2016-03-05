/*global module, require*/
(function () {

    'use strict';

    module.exports = function (grunt) {

        /* Initiate configuration. */
        grunt.initConfig({
            pkg: grunt.file.readJSON('package.json'),
            connect: {
                server: {}
            },
            jasmine: {
                src: 'src/*.js',
                options: {
                    specs: 'test/js/spec/*Spec.js',
                    host: 'http://127.0.0.1:8000/',
                    template: require('grunt-template-jasmine-requirejs'),
                    templateOptions: {
                        requireConfig: {
                            baseUrl: './src',
                            paths: {
                                'jquery': ['../test/js/libs/jquery.min'],
                                'q': ['../test/js/libs/q'],
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
                        }
                    }
                }
            }
        });

        grunt.loadNpmTasks('grunt-contrib-connect');
        grunt.loadNpmTasks('grunt-contrib-jasmine');

        /* Register tasks. */
        grunt.registerTask('test', ['connect', 'jasmine']);
        grunt.registerTask('default', ['test']);

    };

}());