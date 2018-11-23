<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'app',
  }
</script>
<!-- стили компонента -->
<style>
  #app {
    font-family: 'Merriweather', serif, 'Montserrat', sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    margin-top: 0;

    background: #E5E5E5;
    background-size: cover;
  }

  b-button{
    width: 331px;
    height: 88px;
    padding: auto;
    border:solid 4px #000000;

    font: 24px Montserrat, serif;
    font-style: normal;
    font-weight: normal;
    line-height: normal;
    text-align: center;
    text-underline: none;
    background:transparent;
  }

  b-button:hover{ background:#F8D701; }
</style>
