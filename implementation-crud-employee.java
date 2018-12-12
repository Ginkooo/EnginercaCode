@GetMapping(params = { "page", "size" })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_EMPLOYEE')")
    public List<EmployeeResponse> getEmployees(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        List<Employee> employees = employeeService.getEmployees(page, size);
        employees.forEach(employee -> employeeResponses.add(getEmployeeResponse(employee)));
        return employeeResponses;
    }

    @GetMapping(params = { "id" })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_EMPLOYEE')")
    public EmployeeResponse getEmployeeById(@RequestParam("id") long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return getEmployeeResponse(employee);
    }

    @GetMapping(params = { "idUser" })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_EMPLOYEE')")
    public List<EmployeeResponse> getEmployeeByIdUser(@RequestParam("idUser") long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", idUser));
        Employee employee = employeeRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", user.getId()));
        return Arrays.asList(getEmployeeResponse(employee));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
    public EmployeeResponse createEmployee(@Valid @RequestBody AddEmployeeRequest addEmployeeRequest) {
        User user = userRepository.findById(addEmployeeRequest.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", addEmployeeRequest.getIdUser()));

        List<Role> roles = new ArrayList<>();
        addEmployeeRequest.getRoles().forEach(
                idRole -> roles.add(
                        roleRepository.findById(idRole.longValue())
                                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", idRole))));

        Employee employee = new Employee(user,
                addEmployeeRequest.getEmploymentDate(),
                addEmployeeRequest.getFiredDate(),
                addEmployeeRequest.isActive(),
                roles);

        return getEmployeeResponse(employeeRepository.save(employee));
    }

    @PatchMapping(params = { "id" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> partialUpdateEmployee(@RequestParam("id") long id, @RequestBody Map<String, Object> updates) {
        try {
            Employee updatedEmployee = employeeService.updatePartially(updates, id);
            return new ResponseEntity(getEmployeeResponse(updatedEmployee), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Bad request problem!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(params = { "id" })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteEmployee(@RequestParam("id") long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
        return new ResponseEntity(new ApiResponse(true, "Employee deleted"), HttpStatus.OK);
    }
