    @PostMapping(value = "reserve")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public VisitResponse reserveVisit(@CurrentUser CustomUserDetails currentUser, @Valid @RequestBody ReserveVisitRequest reserveVisitRequest) {
        Optional<User> user = userRepository.findById(currentUser.getId());
        Patient patient = patientRepository.findByUser(user.get()).get();
        Doctor doctor = doctorRepository.findById(reserveVisitRequest.getIdDoctor())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", reserveVisitRequest.getIdDoctor()));
        if (visitRepository.existsByVisitTimeAndDoctor(reserveVisitRequest.getVisitTime(), doctor)) {
            throw new BadRequestException("This time for visit is reserved!");
        }
        Visit visit = visitRepository.save(new Visit(doctor, patient, reserveVisitRequest.getVisitTime(), null, false));
        walletService.subtractPointsForVisit(visit, Double.parseDouble(configurationRepository.findByName("defaultVisitPrice").get().getValue()));
        return getVisitResponse(visit);
    }
