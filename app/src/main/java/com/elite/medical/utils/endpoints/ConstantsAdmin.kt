package com.elite.medical.utils.endpoints

object ConstantsAdmin {


    const val BASE_URL = "https://staging.emfwebapp.ikshudigital.com/api/"
    const val URL_FOR_IMAGE = "https://staging.emfwebapp.ikshudigital.com/storage/"

    //        Common
    const val LOGIN = "login"
    const val LOGOUT = "logout"
    const val ADMIN_DASHBOARD_DATA = "dashboard"
    const val CLINIC_DASHBOARD_DATA = "dashboard"
    const val PROFILE_DATA = "profile"
    const val UPDATE_PROFILE = "profile/update"


    //        Registration Routes
    const val REGISTER_NURSE = "nurse/register"
    const val REGISTER_CLINIC = "clinic/register"


    //        Approvals
    const val APPROVALS_LIST_NURSES = "approvals/nurses"
    const val APPROVALS_LIST_CLINICS = "approvals/clinics"
    const val APPROVALS_LIST_JOBS = "approvals/jobs"
    const val APPROVALS_LIST_EMPLOYMENT = "approvals/employments"
    const val APPROVALS_LIST_JOB_SEARCH = "approvals/job-search"

    //      Approvals Buttons- Schedule, Approve, Cancel
    const val APPROVE_USER = "approve-user"     // Works for both Nurse and Clinic
    const val SCHEDULE_NURSE = "approvals/nurses/schedule"
    const val CANCEL_NURSE = "approvals/nurses/cancel"
    const val CANCEL_CLINIC = "approvals/clinics/cancel"
    const val APPROVE_JOB = "approvals/jobs/approve"
    const val CANCEL_JOB = "approvals/jobs/cancel"
    const val APPROVE_EMPLOYMENT = "approvals/employments/approve"
    const val CANCEL_EMPLOYMENT = "approvals/employments/cancel"


    //      navigation menu
    const val NAVIGATION_NOTIFICATIONS = "notifications"
    const val NAVIGATION_NURSE_REVIEWS = "nurse-review"
    const val NAVIGATION_CLINIC_REVIEWS = "clinic-review"
    const val NAVIGATION_CLINICS = "clinics"
    const val NAVIGATION_NURSES = "nurses"
    const val TIMESHEET = "nurses/timesheet"
    const val NAVIGATION_JOBS = "jobs"
    const val NAVIGATION_JOB_APPLICANTS = "job-applicants"


}