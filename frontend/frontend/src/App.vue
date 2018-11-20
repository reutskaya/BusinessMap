<template>
  <div id="app">
   <!-- <router-link to="/">App</router-link>-->
    <router-link to="/about">About</router-link>
    <router-link to="/">Header</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>
<!-- стили компонента -->
<style>
  #app {
    font-family: 'Merriweather', serif, 'Montserrat', sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
   color: #2c3e50;
    margin: 0;
  }

  h1 {
    position: absolute;
    left: 57.36%;
    right: 12.08%;
    top: 5.28%;
    bottom: 79.84%;

    font-family: Merriweather, serif;
    font-style: normal;
    font-weight: normal;
    line-height: normal;
    font-size: 48px;
    text-align: center;
    letter-spacing: -0.1em;
    text-decoration-line: underline;

    color: #000000;
  }
</style>
