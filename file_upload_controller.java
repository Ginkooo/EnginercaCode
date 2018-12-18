    @PostMapping(value = "uploadFile", params = { "id" })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_EMPLOYEE')")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") long id) {
        MedicalCheckup medicalCheckup = medicalCheckupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Checkup", "id", id));
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        medicalCheckup.setResultFile(fileName);
        medicalCheckupRepository.save(medicalCheckup);
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }
