<template>
    <div class="searchfrom">
        <h4>Find by Age</h4>
        <div class="form-group">
            <input type="number" class="form-control"  v-model="age" name="age">
        </div>
        <div class="btn-group">
            <button v-on:click="searchCustomers" class="btn btn-success">Search</button>
        </div>
        <ul class="search=result">
            <li v-for="(customer, index) in customers" v-bind:key="index">
                <h6>{{customer.name}} ({{customer.age}})</h6>
            </li>
        </ul>
    </div>
</template>

<script>
import http from "../http-common";

export default {
    name: "search-customer",
    data() {
        return {
            age: 0,
            customers: []
        }
    },
    methods: {
        searchCustomers() {
            http.get("/customers/age/" + this.age)
            .then(response => {
                this.customers = response.data;
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            })
        }
    }
}
</script>

<style>
.searchform {
    max-width: 300px;
    margin: auto;
}

.search-result {
    margin-top: 20px;
    text-align: left;
}
</style>
