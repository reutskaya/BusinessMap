<template>

  <v-layout row>
    <div id="card">

      <v-card>
        <v-img
          src="src\assets\roman-kraft-156096-unsplash.jpg"
          height="200px"
        >
        </v-img>

        <v-card-title primary-title>
          <div>
            <div class="headline">ЕДА</div>
          </div>
        </v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn icon @click="show = !show">
            <v-icon>{{ show ? 'keyboard_arrow_down' : 'keyboard_arrow_up' }}</v-icon>
          </v-btn>
        </v-card-actions>

        <v-slide-y-transition>
          <v-card-text v-show="show">
            Тут будет анализ
          </v-card-text>
        </v-slide-y-transition>
      </v-card>
    </div>
  </v-layout>
</template>

<script>

</script>

<style scoped>
  #card {
    position: absolute;
    height: 1000px;
    width: auto;
    left: 0px;
    right: 0px;
    top: 1700px;
  }
</style>
