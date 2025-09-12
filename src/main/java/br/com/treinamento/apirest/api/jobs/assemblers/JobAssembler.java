package br.com.treinamento.apirest.api.jobs.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinamento.apirest.api.jobs.controllers.JobRestController;
import br.com.treinamento.apirest.api.jobs.controllers.JobSkillsRestController;
import br.com.treinamento.apirest.api.jobs.dtos.JobResponse;

@Component
public class JobAssembler implements SimpleRepresentationModelAssembler<JobResponse> {

    @Override
    public void addLinks(EntityModel<JobResponse> resource) {
        var id = resource.getContent().getId();

        var selfLink = linkTo(methodOn(JobRestController.class).findById(id))
            .withSelfRel()
            .withType("GET");

        var updateLink = linkTo(methodOn(JobRestController.class).update(null, id))
            .withRel("update")
            .withType("PUT");

        var deleteLink = linkTo(methodOn(JobRestController.class).delete(id))
            .withRel("delete")
            .withType("DELETE");

        var skillsLink = linkTo(methodOn(JobSkillsRestController.class).findSkillsByJobId(id))
            .withRel("skills")
            .withType("GET");

        resource.add(selfLink, updateLink, deleteLink, skillsLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<JobResponse>> resources) {
        var selfLink = linkTo(methodOn(JobRestController.class).findAll(null))
            .withSelfRel()
            .withType("GET");

        var createLink = linkTo(methodOn(JobRestController.class).create(null))
            .withRel("create")
            .withType("POST");

        resources.add(selfLink, createLink);
    }
    
}
