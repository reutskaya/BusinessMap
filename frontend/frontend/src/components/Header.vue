<template>
  <div id="header">
    <h3 id="logo">Business Map</h3>
    <h1 id="myheadline">Начни свой бизнес уже сейчас</h1>
    <h1 id="subgolem">А мы поможем тебе изучить рынок и выбрать нишу для бизнеса</h1>
      <b-button class="btn" v-on:click="scroll">
        <span id="button-text">Начать</span>
      </b-button>
      <mapa/>
      <card/>
  </div>
</template>

<script>
  import mapa from './Map'
  import card from './Card'
  export default {
    name: 'header',
    components: {mapa, card},
    methods:{
      scroll: function () {
        window.scrollTo(0, 777)
      }
    }
  };
/*
  var container = this.$el.querySelector("#header");
  container.scrollTop = container.scrollHeight;*/

  /*(document).ready(function(){
    ("down").on("click","b-button", function (event) {
      //отменяем стандартную обработку нажатия по ссылке
      event.preventDefault();
      //забираем идентификатор бока с атрибута href
      var id  = $(this).attr('href'),
      //узнаем высоту от начала страницы до блока на который ссылается якорь
      top = (id).offset().top;
      //анимируем переход на расстояние - top за 1500 мс
      ('body,html').animate({scrollTop: top}, 1500);
    });

  });*/

</script>
<!-- инкапсулированные стили компонента -->
<style lang="css">
  #header {
    position: absolute;
    font-family: 'Merriweather', serif, 'Montserrat', sans-serif;
    -webkit-font-smoothing: antialiased;
    width: 100%;
    padding: 0;
    float: left;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    margin: 0;
    height: 850px;
    background: url("../assets/1.jpg") no-repeat center center;
    background-size: cover;
  }

  #logo {
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
  
  #myheadline {
    position: absolute;
    /*left: 35.14%;*/
    right: 7.92%;
    top: 46.96%;
    /*bottom: 34.37%;*/

    font-family: Merriweather,serif;
    font-style: normal;
    font-weight: normal;
    line-height: normal;
    font-size: 48px;
    text-align: center;

    color: #000000;
  }

  #subgolem{
    position: absolute;
    /*left: 41.53%;*/
    right: 14.31%;
    top: 58.46%;
    /*bottom: 36.67%;*/

    font-family: Montserrat,serif;
    font-style: normal;
    font-weight: normal;
    line-height: normal;
    font-size: 18px;
    text-align: center;

    color: #000000;
  }

  .btn{
    position: absolute;
    /*left: 54.17%;*/
    right: 26.94%;
    top: 69.28%;
    /*bottom: 24.76%;*/

    width: 331px;
    height: 88px;
    border:solid 4px rgba(0, 0, 0, 0.63);

    font: 24px Montserrat, serif;
    font-style: normal;
    font-weight: normal;
    line-height: normal;
    text-align: center;
    text-underline: none;

    background:transparent;

  }

  .btn:hover{
       background:#F8D701;
   }

  #button-text{
    left: 7px;
    top: 21px;

    font: 24px Montserrat, serif;
    font-style: normal;
    font-weight: normal;
    line-height: normal;
    text-align: center;
    text-underline: none;
    color: #000000;
  }
</style>
