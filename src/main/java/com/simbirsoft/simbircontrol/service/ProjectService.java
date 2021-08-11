package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.filter.Condition;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {

    /**
     * get all projects
     * @return list projects
     */
    List<ProjectResponseDto> getAll();

    /**
     * get project by his id
     * @param id id project
     * @return project
     */
    ProjectResponseDto getById(Integer id);

    /**
     * create new project
     * @param requestDto properties new project
     * @return new project
     */
    ProjectResponseDto create(ProjectRequestDto requestDto);

    /**
     * update old project new values
     * @param requestDto new values project
     * @return project
     */
    ProjectResponseDto update(ProjectRequestDto requestDto);

    /**
     * delete project by his id
     * @param id id project
     */
    void deleteById(Integer id);

    /**
     * get all releases by id project
     * @param id id project
     * @return list releases
     */
    List<ReleaseResponseDto> getReleasesProject(Integer id);

    /**
     * get all tasks by id project
     * @param id id project
     * @return list tasks
     */
    List<TaskResponseDto> getTasksProject(Integer id);

    /**
     * upload tasks from file .csv
     * @param id id project
     * @param file csv file
     */
    void addTasksFromCSV(Integer id, MultipartFile file);

    /**
     * get all tasks appropriate filter
     * @param id id project
     * @param conditions conditions filter
     * @return filtered tasks
     */
    List<TaskResponseDto> getFilteredTasksProject(Integer id, List<Condition> conditions);
}
