function User(fullName) {
    this.fullName = fullName;

    Object.defineProperties(this, {

        firstName: {

            get: function() {
                return this.fullName.split(' ')[0];
            },

            set: function(newFirstName) {
                this.fullName = newFirstName + ' ' + this.lastName + ' ' + this.patronym;
            }

        },

        lastName: {

            get: function() {
                return this.fullName.split(' ')[1];
            },

            set: function(newLastName) {
                this.fullName = this.firstName + ' ' + newLastName + this.patronym;
            }

        },

        patronym: {

            get: function () {
                return this.fullName.split(' ')[2];
            },

            set: function(newPatronym) {
                this.fullName = this.firstName;
            }
        }
    });
}

var vanya = new User("Иван Васильевич Петров");

alert( vanya.firstName );
alert( vanya.lastName );
alert( vanya.patronym );

vanya.lastName = 'Сидоров';

alert( vanya.fullName );