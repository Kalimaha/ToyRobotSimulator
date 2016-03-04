/*global describe, SIMULATOR, beforeEach, it, expect*/
describe('Toy Robot Simulator', function () {

    'use strict';

    var s;

    beforeEach(function () {
        s = new SIMULATOR();
    });

    it('should have a version number', function () {
        expect(s.CONFIG.version).not.toBeUndefined();
    });

    it('with current version equal to 0.1', function () {
        expect(s.CONFIG.version).toEqual(0.1);
    });

});