<template>
  <v-layout row>
    <v-flex xs12 sm6 offset-sm3>
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

        <v-slide-y-transition>
          <v-card-text v-show="show">
            Тут будет анализ
          </v-card-text>
        </v-slide-y-transition>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>

</script>

<style scoped>

</style>
