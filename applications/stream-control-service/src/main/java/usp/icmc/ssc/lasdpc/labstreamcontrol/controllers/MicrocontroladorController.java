package usp.icmc.ssc.lasdpc.labstreamcontrol.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.resps.MicrocontroladorResp;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.services.MicrocontroladorService;

@RestController
@RequestMapping(value = "api/v1/microcontroladores")
public class MicrocontroladorController {
    
    @Autowired
    private MicrocontroladorService microcontroladorService;

    @PostMapping
    public Microcontrolador register(@RequestBody MicrocontroladorResp microcontroladorResp) {
        return this.microcontroladorService.register(microcontroladorResp);
    }

    @GetMapping("/{id}")
    public MicrocontroladorResp getMicrocontroladorData(@PathVariable("id") UUID id) {
        return this.microcontroladorService.getMicrocontroladorComSensoresAtuadores(id);
    }

}
