<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>Customers List</h4>
            <ul>
                <li v-for="(customer,index) in customers" v-bind:key="index">
                    <router-link v-bind:to="{
                        name: 'customer-details',
                        params: {
                            customer: customer,
                            id: customer.id
                        }
                    }">
                        {{customer.name}}   
                    </router-link>
                </li>
            </ul>
        </div>
        <div class="col-md-6">
            <router-view v-on:refreshData="refreshList"></router-view>
        </div>
    </div>    
</template>

<script>
import http from "../http-common";

export default {
    name: "customers-list",
    data() {
        return {
            customers: []
        }
    },
    methods: {
        retrieveCustomers() {
            http
                .get("/customers")
                .then(response => {
                    this.customers = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                })
        },
        refreshList() {
            this.retrieveCustomers();
        }
    },
    mounted() {
        this.retrieveCustomers();
    }
}
</script>

<style>
.list {
    text-align: left;
    max-width: 450px;
    margin: auto;
}
</style>
