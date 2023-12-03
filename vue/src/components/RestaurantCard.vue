<template>
    <h1>INSERT RESTAURANT HERE</h1>
</template>

<script>
export default {

    data() {
        return {
            restaurant:
            {
                id: '',
                name: '',
                imgUrl: '',
                category: {
                    title: '',
                    alias: ''
                },
                rating: 0,
                transactionTypes: [],
                price: '',
                location: {
                    city: '',
                    zipCode: ''
                }
            }
        }
    }
};
</script>

<style></style>