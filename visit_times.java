    @GetMapping(value = "visitTimes", params = "idSchedule")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_PATIENT')")
    public List<VisitTimesResponse> getVisitTimesByIdSchedule(@RequestParam("idSchedule") Long idSchedule) {
        List<VisitTimesResponse> visitTimes = new ArrayList<>();
        Schedule schedule = scheduleRepository.findById(idSchedule)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", idSchedule));
        Date date = schedule.getStartTime();
        while (date.before(schedule.getEndTime())) {
            visitTimes.add(new VisitTimesResponse(date, !visitRepository.existsByVisitTimeAndDoctor(date, schedule.getDoctor())));
            date = DateUtils.addMinutes(date, schedule.getSpecialization().getVisitDuration());
        }

        return visitTimes;
    }
