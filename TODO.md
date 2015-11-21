## Bug/task tracker functionality ##

**Key:**

 * #feature our current software doesn't have which I'd like to add
 * !feature our current software has, but we don't use and I'd like to drop

	Tasks
		Attributes
			Name
			Description
			Notes
			#Category ("Project"; A1, A6, etc)
			Project (I'd like to re-purpose this for its actual purpose, by adding Category to do what we use Projects for)
			Flow State (Needs Testing on Test, etc)
			Assigned Programmers
			Assigned Testers (by Flow State)
			People wanting updates
			Created by
			Child of task (found during task, spawned by task)
			Parent of tasks
			Dates
				Created on
				#Started work on
				#Finished programming on
			Priority (I'd like to expand this to 2x or 3x attributes which combine to determine overall priority)
			Scheduled Deploy
			Git branch
			Flags
				Tax Creditable
				Checked Client Side (P, T, S)
				Checked Mobile App (P, T, S)
				Checked Reports (P, T, S)
				Task Info DAO (P)
			Tags
			!Progress
		Logs
			Date + Time
			Hours
			User
			Comments
			!summary (which we don't really use anyway)
			#reply to log
		Files
	
	Projects
		Name
		Short Name
		Description
		Color
		!Priority
		!Progress
		!Owner (#could change to assignees, if we use projects as intended)
	
	#Categories (replace how we currently use Projects)
		#Name (eg "A1-Stability Tasks")
		#Description
		#Abbreviation (eg "A1")
		#Priority/Sort Order
		#Color
	
	Deploys
		Name
		Target Date
		Actual Date
		State (planned, deployed, deleted)
		Tasks
		Tags
	
	Users
		username
		User Type (Programmer, Customer Support, etc)
		Department (conflated with User Type)
		Company
		Contact record
		Preferences (for system settings which may vary by user)
	
	Contacts (mostly unused)
		Name
		Email
		!misc contact info which we don't use
		Notes
		
	Disabled/Hidden Features
		Calendar
		Companies
			Departments
		Forums
		Tickets
		links (?)
		resources (?)
		history (?)
	
	Misc Features
		Search Tasks
		Dashboards -> a view of tasks sorted and grouped by various attributes
		Merge List -> list tasks which need to be merged into the Test server
		Tax -> reports tabulating data about tasks
		User Log -> reports on logins for a user
	
	Config Features
		Tags
		Files -> manage uploaded files
		System Admin -> misc system settings
			!Language/i18n management
			Default user settings
			Custom Field editor
			Module management (controls top navigation, too)
			User Role management (permission groups)
			DB Maintenance
			Import tools
		User Admin -> role control, create new users
	
	Web Services
		Does Test Need To Deploy
		Test Merge List
	