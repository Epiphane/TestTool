'use strict';
var gulp = require('gulp');
var browserSync = require('browser-sync');
var rimraf = require('gulp-rimraf');
var source = require('vinyl-source-stream');
var browserify = require('browserify');
var html2react = require('gulp-html2react');
var changed = require('gulp-changed');
var debowerify = require('debowerify');

gulp.task('clean', function() {
  return gulp.src('dist', {
    read: false
  })
    .pipe(rimraf({
  force: true
    }));
});

gulp.task('browserSync', function() {
  browserSync.init(['dist/**'], {
    server: {
      baseDir: 'dist'
    },
    port: process.env.PORT || 3000
  });
});

gulp.task('react', function() {
  return gulp.src('html/javascripts/templates/**/*.html')
  .pipe(html2react())
  .pipe(gulp.dest('temp/javascripts/templates'));
});

gulp.task('copy_js', function() {
  var files = ['html/javascripts/**/*.js'];
  return gulp.src(files).pipe(gulp.dest('temp/javascripts'));
});

gulp.task('copy', ['copy_js'], function() {
  var files = ['html/**/*', '!html/javascripts', '!html/javascripts/**/*'];
  var DEST = 'dist'

  return gulp.src(files).pipe(changed(DEST)).pipe(gulp.dest(DEST));
});

// using vinyl-source-stream:
gulp.task('browserify', ['copy', 'react'], function() {
  var bundleStream = browserify('./temp/javascripts/app.js').transform(debowerify).bundle();
  bundleStream
  .pipe(source('./javascripts/app.js'))
  .pipe(gulp.dest('./dist/'));
});

gulp.task('default', ['browserify', 'browserSync'], function() {
  gulp.watch('html/*.html', ['copy']);
  gulp.watch('html/stylesheets/**/*', ['copy']);

  gulp.watch('html/javascripts/**/*', ['browserify']);
});
