<template>
  <div id="header">
      <h3>Business Map</h3>
    <!--<img src="../assets/1.jpg">
    -->
  </div>
</template>

<script>
  export default {
    name: 'header'
  };
</script>
<!-- инкапсулированные стили компонента -->
<style lang="css">
  #header {
    font-family: 'Merriweather', serif, 'Montserrat', sans-serif;
    -webkit-font-smoothing: antialiased;
    width: 100%;
    padding: 0;
    position: relative;
    float: left;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    margin: 0px;
    height: 850px;
    background-image: url("../assets/1.jpg");
    background-size: cover;
  }

  h3 {
    position: absolute;
    left: 57.36%;
    right: 12.08%;
    top: 5.28%;
    bottom: 79.84%;

    font-family: Merriweather,serif;
    font-style: normal;
    font-weight: normal;
    line-height: normal;
    font-size: 48px;
    text-align: center;
    letter-spacing: -0.1em;
    text-decoration-line: underline;

    color: #000000;

  }

   img {
    height: 100%;
    width: 100%;
    object-fit: cover;
    position: absolute;
    z-index: 1;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    filter: brightness(70%);
  }
</style>
