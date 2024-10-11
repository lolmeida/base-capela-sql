#!/bin/bash

ENV="${1:-emea-int}"

ijhttp/ijhttp \
  Get-HealthCheck.http \
  Post-AddAsset.http \
  Post-AddAsset-BadRequest.http \
  Post-AddAsset-FileExtensionMismatch.http \
  Post-AddAsset-Forbidden.http \
  Post-AddAsset-Unauthorized.http \
  Post-AddTheme.http \
  Delete-DeleteThemeNotExpired.http \
  Post-AddTheme-BadRequest.http \
  Post-AddTheme-Forbidden.http \
  Post-AddTheme-Unauthorized.http \
  Get-ThemeById.http \
  Get-ThemeById-NotFound.http \
  Get-AllThemesAdmin.http \
  Get-AllThemesAdmin-Forbidden.http \
  Get-AllThemesAdmin-Unauthorized.http \
  Put-UpdateThemeMetadata.http \
  Put-UpdateThemeMetadata-BadRequest.http \
  Put-UpdateThemeMetadata-Forbidden.http \
  Put-UpdateThemeMetadata-Unauthorized.http \
  Put-UpdateThemeRequirements.http \
  Put-UpdateThemeTranslations.http \
  Put-UpdateThemeAdminInfo.http \
  Put-UpdateThemeConfiguration.http \
  Put-UpdateThemeConfiguration-CountryListAndFalseAutoApply.http \
  Put-UpdateThemeConfiguration-AutoApplyListHasInvalidCountry.http \
  Put-UpdateThemeStyles.http \
  Put-UpdateToInvalidStatus.http \
  Put-UpdateStatusToUnexistingState.http \
  Put-UpdateStatus-Testing.http \
  Get-ThemeById.http \
  catalog-requests/Get-ValidateCatalogData.http \
  catalog-requests/Get-GetThemeExperiences-WithDebug.http \
  catalog-requests/Get-GetThemeExperiences-NoDebug.http \
  Put-UpdateStatus-Validation.http \
  catalog-requests/Get-GetThemeExperiences-WithDebug.http \
  catalog-requests/Get-GetThemeExperiences-NoDebug.http \
  Put-UpdateStatus-Published.http \
  Post-AddTheme-NewVersion.http \
  Post-AddTheme-CloneNotPublishedTheme.http \
  Put-UpdateThemeMetadata-NewVersion.http \
  Get-ThemeById.http \
  Get-ThemeById-ThemeVersioned.http \
  Put-UpdateStatus-ValidateNewThemeVersion.http \
  Put-UpdateStatus-PublishNewThemeVersion.http \
  Get-ThemeById-NewThemeVersionWithoutVinHeader.http \
  Get-ThemeById-NewThemeVersionWithVinHeader.http \
  Get-ThemeById-NewThemeVersionedNotFound.http \
  Get-AllThemes.http \
  --env-file environment/http-client.env.json --env "$ENV" --insecure --report
