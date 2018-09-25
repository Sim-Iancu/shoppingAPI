package shopifyapi.controller;

import org.springframework.web.bind.annotation.*;
import shopifyapi.manager.Manager;
import javax.ws.rs.core.Response;

import static org.springframework.web.bind.annotation.RequestMethod.*;

public abstract class BaseController<T, M extends Manager<T>> {

    protected M manager;

    protected BaseController(M manager) {
        this.manager = manager;
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public Iterable<T> retrieveOne() {
        return manager.getAll();
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public T retrieveAll(@PathVariable(value = "id") Long id) {
        return manager.get(id);
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public T create(@RequestBody T model) {
        return manager.save(model);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        manager.delete(id);
    }
}
