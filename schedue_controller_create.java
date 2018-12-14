    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_EMPLOYEE')")
    public ScheduleResponse createSchedule(@Valid @RequestBody AddScheduleRequest addScheduleRequest) {
        Doctor doctor = doctorRepository.findById(addScheduleRequest.getIdDoctor())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", addScheduleRequest.getIdDoctor()));

        Office office = officeRepository.findById(addScheduleRequest.getIdOffice())
                .orElseThrow(() -> new ResourceNotFoundException("Office", "id", addScheduleRequest.getIdOffice()));

        Specialization specialization = specializationRepository.findById(addScheduleRequest.getIdSpecialization())
                .orElseThrow(() -> new ResourceNotFoundException("Specialization", "id", addScheduleRequest.getIdSpecialization()));

        Schedule schedule = new Schedule(doctor, office,
                addScheduleRequest.getStartTime(), addScheduleRequest.getEndTime(), specialization);

        if (!scheduleService.validSchedule(schedule)) {
            throw new BadRequestException("Incorrect days and hours!");
        }

        return getScheduleResponse(scheduleRepository.save(schedule));
    }
