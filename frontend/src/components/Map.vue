<!--suppress ALL -->
<template>
   <div id="map-area">
     <h1 class="headline" id="hl1">выбери точку на карте | определи нужный радиус | получи результат</h1>
     <div id="line"
          style="top: 10.64%;
          bottom: 86.97%;">
     </div>
     <yandex-map
       id="yandexMap"
       :coords="mapCoords"
       zoom="12"
       :cluster-options="{
    1: {}
  }"
       :behaviors="['default']"
       :controls="['fullscreenControl', 'geolocationControl', 'searchControl', 'zoomControl']"
       :placemarks="placemarks"
       map-type="map"
       @map-was-initialized="onMapInit"
     >
       <ymap-marker
         marker-id="3"
         marker-type="placemark"
         :coords="circleCoords"
         hint-content="центр области"
         :balloon="{header: 'header', body: 'body', footer: 'footer'}"
         :icon="{color: 'gray', glyph: 'DotIcon'}"
         cluster-name="1"
       ></ymap-marker>
       <ymap-marker
         marker-id="1"
         marker-type="circle"
         :coords="circleCoords"
         :circle-radius="+radToMetres(radius)"
         hint-content="выбранная область"
         :marker-fill="{color: '#000000', opacity: 0.4}"
         :marker-stroke="{color: '#ffe222', width: 3}"
         :balloon="{header: 'Санкт-Петербург', body: 'выбранная область для анализа', footer: ''}"
       ></ymap-marker>

     </yandex-map>
     <div id="line" style="
     top: 97.61%;
     bottom: 0%;">
     </div>

     <h1 class="headline" id="hl2">размер области поиска {{radius}}</h1>
     <div class="circle-bar">
       <div id="c1" class="circle" v-on:click="select(0.5)">
         <h2 id="r1" class="hover-bar">&#189</h2>
       </div>
       <div id="c2" class="circle" v-on:click="select(1)">
         <h2 id="r2" class="hover-bar">1 км</h2>
       </div>
       <div id="c3" class="circle" v-on:click="select(3)">
         <h2 id="r3" class="hover-bar" for="c3">3 км</h2>
       </div>
       <div id="c4" class="circle" v-on:click="select(5)">
         <h2 id="r4" class="hover-bar">5 км</h2>
       </div>
     </div>

     <b-button class="btn" v-on:click="find">
       <span id="button-text">Анализ области</span>
     </b-button>
     <div id="cards" v-if="response != null">
       <card v-bind:category="parseCategory('Еда') "/>
       <card v-bind:category="parseCategory('Развлечения') "/>
       <card v-bind:category="parseCategory('Гостиницы') "/>
       <card v-bind:category="parseCategory('Покупки') "/>
       <card v-bind:category="parseCategory('Красота') "/>
       <card v-bind:category="parseCategory('Здоровье') "/>
     </div>
   </div>
</template>

<script>
  import { yandexMap, ymapMarker } from 'vue-yandex-maps'
  import Card from "./Card"
  import axios from 'axios';

  export default {
    name: 'map-area',
    components: {Card, yandexMap},
    data()
    {
      return {
        placemarks: [],
        mapCoords: [59.9386300, 30.3141300],
        circleCoords: [59.9386300, 30.3141300],
        address: '',
        value: 25,
        radius:'1',
        response: null
      }
    },
    methods:{
      onMapClick : function (coords) {

        this.circleCoords = coords
        this.mapCoords = coords
        this.placemarks.push({asd:1})
        this.placemarks.pop()
      },

      onMapInit : function(map) {
        var self = this;
        map.events.add('click', function(e){
          self.onMapClick(e.get('coords'))
        })
      },
      select: function (r) {
        this.radius = r
      },
      getRequestText: function ([a, b]) {
        return String(a) + "/" + String(b) + "/" + String(this.radius)
      },
      find : function () {
          axios
            .get("http://localhost:8080/places/" + this.getRequestText(this.circleCoords))
            .then(response2 => (this.response = response2.data));
      },
      parseCategory : function (category) {
        if (this.response!= null){
          return this.response.filter(function (item) {
            return item.name == category;
          })[0]
        }
      },
      radToMetres: function(rad){
        return +rad*1000;
      }
    }
  }
</script>

<style scoped>
  #map-area{

    position: absolute;
    height: 752px;
    width: auto;
    left: 0px;
    right: 0px;
    top: 777px;
    background: #E5E5E5;
  }

  .headline{
    font-family: Montserrat,serif;
    font-style: normal;
    /*font-weight: 500;*/
    line-height: normal;
    font-size: 24px;
    text-align: center;

    color: #000000;
  }

  #hl1{
    position: absolute;
    left: 18.47%;
    right: 14.31%;
    top: 2.39%;
    bottom: 88.56%;
  }

  #hl2{
    position: absolute;
    left: 65.16%;
    right: 3.96%;
    top: 29.26%;
    bottom: 63.83%;
  }

  #line{
    position: absolute;
    left: 0%;
    right: 0%;
    width: auto;

    background: #F8D701;
  }

  #yandexMap{
    position: absolute;
    left: 0%;
    right: 38.19%;
    top: 13.03%;
    bottom: 2.39%;
  }

  .hover-bar{
    position: relative;
    unselectable: on;
    top: 40%;
    text-align: center;
    font-size: 16px;
    display: none;
  }

  .circle{
    background: rgba(248, 215, 1, 0.46);
    /*-moz-border-radius: 50px;
    -webkit-border-radius: 50px;*/
    border: 2px solid #ffe222;
  }

  .circle:hover .hover-bar{display: block;}
  .circle:active .hover-bar{display: block;}
  .circle:active{
    background: #ffe222;
  }

  #c1{
    position: absolute;
    height: 38px;
    width: 38px;
    /*left: 67.25%;*/
    right: 30.11%;
    top: 47.47%;
   /* bottom: 47.47%;*/
    border-radius: 38px;
  }

  #c2{
    position: absolute;
    height: 62px;
    width: 62px;
    /*left: 72.39%;*/
    right: 23.3%;
    top: 45.88%;
    /*bottom: 45.88%;*/
    border-radius: 62px;
  }

  #c3{
    position: absolute;
    height: 83px;
    width: 83px;
    /*left: 79.21%;*/
    right: 15.02%;
    top: 44.41%;
    /*bottom: 44.55%;*/
    border-radius: 83px;
  }
  #c4{
  position: absolute;
  height: 117px;
  width: 117px;
  /*left: 87.48%;*/
  right: 4.38%;
  top: 42.15%;
  /*bottom: 42.29%;*/
  border-radius: 117px;
}

  .btn{
    position: absolute;
    width: 331px;
    height: 88px;
    left: 69.47%;
    right: 7.51%;
    top: 71.81%;
    bottom: 16.49%;
    border: 4px solid rgba(0, 0, 0, 0.63);

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

  #cards{
    background: #E5E5E5;
    position: absolute;
    top: 750px;
  }
</style>
