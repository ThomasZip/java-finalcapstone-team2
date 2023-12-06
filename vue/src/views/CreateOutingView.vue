<template>
    <h1>create outing</h1>
    <div class="input-div">
        <label for="nameInput">Name Your Outing:</label>
        <input id="nameInput" name="nameInput" type="text" v-model="nameInput" v-bind:required="true"/>
    </div>
    <div class="input-div">
        <label for="zipcodeInput">Enter a Zipcode (required):</label>
        <input id="zipcodeInput" name="zipcodeInput" type="text" v-model="zipcodeInput"/>
    </div>
    <div class="input-div">
        <label for="deadlineInput">Date of final decision:</label>
        <input id="deadlineInput" name="deadlineInput" type="datetime-local" v-model="deadlineInput" v-bind:required="true"/>
    </div>
    <div class="input-div">
        <label for="eventInput">Date of actual event:</label>
        <input id="eventInput" name="eventInput" type="datetime-local" v-model="eventInput" v-bind:required="true"/>
    </div>
    <div id="button-div">
      <button type="submit">Create Outing</button>
      </div>
</template>
<!-- Does this push user to restaurants list? -->

<script></script>

<style></style>