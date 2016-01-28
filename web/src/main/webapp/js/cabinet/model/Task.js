/**
 * Created by al1 on 06.06.15.
 */
define(function(){

    function Task(name, mark){
        this.name = name || 'Default name';
        this.name = mark || 'Default mark';
    }

    return Task;
});